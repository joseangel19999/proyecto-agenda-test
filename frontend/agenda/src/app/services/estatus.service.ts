import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EstatusResponse } from '../models/estatus.response.model';

@Injectable({
  providedIn: 'root'
})
export class EstatusService {

  private apiUrl: string = environment.api.protocol.concat(environment.api.baseUrl);

  constructor(private http: HttpClient) { }

  listarEstatus():Observable<EstatusResponse[]>{
    return this.http.get<EstatusResponse[]>(`${this.apiUrl}/estatus`);
  }
}
