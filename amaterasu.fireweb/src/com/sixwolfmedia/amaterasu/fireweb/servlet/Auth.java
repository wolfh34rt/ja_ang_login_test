package com.sixwolfmedia.amaterasu.fireweb.servlet;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.internal.oxm.conversion.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sixwolfmedia.amaterasu.db.DbContext;
import com.sixwolfmedia.amaterasu.db.Person;
import com.sixwolfmedia.amaterasu.db.PersonObject;
import com.sixwolfmedia.amaterasu.fireweb.jsonobjects.Security;
import com.sixwolfmedia.amaterasu.fireweb.jsonobjects.AppConfig;
import com.sixwolfmedia.amaterasu.fireweb.jsonobjects.AuthorizationTokens;
import com.sixwolfmedia.amaterasu.fireweb.jsonobjects.ErrorMessage;
import com.sixwolfmedia.amaterasu.fireweb.utils.Helpers;

@WebServlet("/auth/*")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Auth() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		AppConfig config = mapper.readValue(Helpers.ReadInputStreamToString(
				request.getServletContext().getResourceAsStream("/WEB-INF/app_config.json")), AppConfig.class);
		DbContext context = new DbContext(config.getMySqlConnectionString(), config.getMySqlUsername(),
				config.getMySqlPassword(), config.getMySqlDriver());

		/// TODO: move most of the db interaction into business logic package
		try {
			if (request.getPathInfo().toLowerCase().equals("/login")) {
				String authentication_data = request.getHeader("Authorization");
				String json_message;

				if (authentication_data != null) {
					Person person = null;
					PersonObject security = null;
					Security json_security = null;
					String[] decoded_login_info = new String(
							Base64.base64Decode(authentication_data.split(" ")[1].getBytes())).split(":");
					Query person_query = context.getEntityManager().createNamedQuery("Person.findPersonByName");
					Query person_objects_query = context.getEntityManager()
							.createNamedQuery("PersonObject.findSecurityObjectByPersonId");

					person_query.setParameter("name", decoded_login_info[0]);

					try {
						person = (Person) person_query.getSingleResult();
						person_objects_query.setParameter("personId", person);
						security = (PersonObject) person_objects_query.getSingleResult();
						json_security = mapper.readValue(security.getJsonData(), Security.class);

						if (json_security.getSecurity().getPw_hash()
								.equals(Helpers.GetMD5Hash(decoded_login_info[1]))) {
							String person_id = person.getPersonId();
							
							///TODO: Create full auth token logic. Placeholder for now
							json_message = mapper.writeValueAsString(new AuthorizationTokens() {
								{
									setPerson_id(person_id);
									setAccess_token(Helpers.GenerateIdentityKey());
									setRefresh_token(Helpers.GenerateIdentityKey());
								}
							});
							//
							
							response.setStatus(HttpServletResponse.SC_OK);
						} else {
							json_message = mapper.writeValueAsString(new ErrorMessage() {
								{
									setError("Invalid credentials.");
								}
							});
							
							response.setStatus(HttpServletResponse.SC_OK);
						}
					} catch (NoResultException e) {
						json_message = mapper.writeValueAsString(new ErrorMessage() {
							{
								setError("Invalid credentials.");
							}
						});
						
						response.setStatus(HttpServletResponse.SC_OK);
					}
				} else {
					json_message = mapper.writeValueAsString(new ErrorMessage() {
						{
							setError("Invalid Authentication Header.");
						}
					});
					
					response.setStatus(HttpServletResponse.SC_OK);
				}

				response.getWriter().append(json_message);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			context.getEntityManager().close();
		}
	}
}
