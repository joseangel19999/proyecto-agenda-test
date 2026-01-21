import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ActividadResponse } from '../models/actividad.model.response';
import { Observable } from 'rxjs';
import { ActividadRequest } from '../models/actividad.model.request';

@Injectable({
  providedIn: 'root'
})
export class ActividadService {

  private apiUrl: string = environment.api.protocol.concat(environment.api.baseUrl);
  constructor(private http: HttpClient) { }

  save(cuenta: ActividadRequest): Observable<ActividadResponse> {
    return this.http.post<ActividadResponse>(`${this.apiUrl}/actividad`, cuenta);
  }

  listarActividades():Observable<ActividadResponse[]>{
    return this.http.get<ActividadResponse[]>(`${this.apiUrl}/actividad`);
  }
  delete(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/actividad/${id}`);
  }
}
