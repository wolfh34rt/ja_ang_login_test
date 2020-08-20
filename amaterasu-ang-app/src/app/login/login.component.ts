import { Component, OnInit, ElementRef } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { NgForm } from "@angular/forms";
import { environment } from "../../environments/environment";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    constructor(private componentDocument: ElementRef, private http_client: HttpClient) { }

    public ngOnInit() {

    }

    public onSubmit(login_form: NgForm) { 
        let httpHeaders: any;
        let re_only_alphanumeric = RegExp("^[a-z0-9]+$");
        let message_div = this.componentDocument.nativeElement.querySelector(".message_div")
        let person_name = login_form.value.person_name;
        let person_password = login_form.value.person_password;
        let error_message: string = "";
        message_div.innerHTML  = "";

        if(!re_only_alphanumeric.test(person_name)) {
            error_message = "Username must not contain special chars or uppercase characters.<br />";
        }

        if(login_form.value.person_name.length < 2) {
            error_message += "Length of username must be greater than 1";
        }

        if(error_message.length > 0) {
            message_div.innerHTML = error_message;
        } else {
            httpHeaders = { "Authorization" : "Basic " + btoa(person_name + ":" + person_password) };
            /// TODO: Move api url to config service & move external api interaction into service file.
            this.http_client.post<any>(environment.base_url + "/auth/login",{}, { headers: httpHeaders }).subscribe(response => {
                if(response.error) {
                    message_div.innerHTML = response.error;
                } else {
                    if(response.access_token && response.refresh_token) {
                        /// TODO: process and store access token to localStorage, Placeholder for now.
                        message_div.innerHTML = "access token : " + response.access_token + "<br />refresh token: " + response.refresh_token
                    } else {
                        error_message = "Invalid validation tokens returned.";
                        message_div.innerHTML = error_message;
                    }
                }
            });
        }
    }
}