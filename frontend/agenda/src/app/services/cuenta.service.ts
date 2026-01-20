import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CuentaRequest } from '../models/cuenta.model.request';
import { Observable } from 'rxjs';
import { CuentaResponse } from '../models/cuenta.model.response';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  private apiUrl: string = environment.api.protocol.concat(environment.api.baseUrl);

  constructor(private http: HttpClient) { }

  findAll(): Observable<CuentaResponse[]> {
    return this.http.get<CuentaResponse[]>(`${this.apiUrl}/persona`);
  }

  findAllBySilo(email: number): Observable<CuentaResponse[]> {
    let params = new HttpParams();
    params = params.set("email", email);
    return this.http.get<CuentaResponse[]>(`${this.apiUrl}/persona/filter-email`, { params });
  }

  save(cuenta: CuentaRequest): Observable<CuentaResponse> {
    return this.http.post<CuentaResponse>(`${this.apiUrl}/persona`, cuenta);
  }

  update(cuenta: CuentaRequest, id: number): Observable<CuentaResponse> {
    return this.http.put<CuentaResponse>(`${this.apiUrl}/persona/${id}`, cuenta);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/persona/${id}`);
  }

}
