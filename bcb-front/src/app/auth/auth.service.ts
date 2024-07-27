import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // httpClient = inject(HttpClient);
  // baseUrl = 'http://localhost:8080/';

  login(data: User) {
    if(data.getName() === "admin" && data.getPassword() === "1234"){
      localStorage.setItem('authUser', JSON.stringify(data.getName()+data.getPassword()));
    }
    // return this.httpClient.post(`${this.baseUrl}/login`, data)
    //   .pipe(tap((result) => {
    //     localStorage.setItem('authUser', JSON.stringify(result));
    //   }));
  }

  logout() {
    localStorage.removeItem('authUser');
  }

  isLoggedIn() {
    return localStorage.getItem('authUser') !== null;
  }
}
