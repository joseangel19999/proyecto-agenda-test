import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { LoginRequest } from '../models/login.model.request';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login.model.response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl: string = environment.api.protocol.concat(environment.api.baseUrl);

  constructor(private http:HttpClient) {
  }

  autenticarUsuario(cuenta:LoginRequest):Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.apiUrl}/auth/login`, cuenta);
  }
}
