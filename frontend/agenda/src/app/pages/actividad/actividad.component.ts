import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { RegistroActividadComponent } from './registro-actividad/registro-actividad.component';
import { ListarActividadComponent } from './listar-actividad/listar-actividad.component';
import { ActividadResponse } from '../../models/actividad.model.response';
import { ActividadService } from '../../services/actividad.service';

@Component({
  selector: 'app-actividad',
  imports: [HeaderComponent,RegistroActividadComponent,ListarActividadComponent],
  templateUrl: './actividad.component.html',
  styleUrl: './actividad.component.css'
})
export class ActividadComponent implements OnInit{

  lista:ActividadResponse[]=[];

  constructor(private actividadService:ActividadService){
  }

  ngOnInit(): void {
    this.listarActividades();
  }

  listarActividades(){
    this.actividadService.listarActividades().subscribe({
      next:(response:ActividadResponse[])=>{
        this.lista=response;
        console.log("DATA LISTA ACTIVIDADES: "+JSON.stringify(response));
      },error(err) {
        console.log("DATA ERROR :"+JSON.stringify(err));
      },
    })
  }


}
