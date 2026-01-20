import { ActividadResponse } from './../../../models/actividad.model.response';
import { Observable } from 'rxjs';
import { ListarActividadComponent } from './../listar-actividad/listar-actividad.component';
import { ActividadService } from './../../../services/actividad.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgbPaginationModule, NgbTypeaheadModule } from '@ng-bootstrap/ng-bootstrap';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RouterModule } from '@angular/router';
import { ActividadRequest } from '../../../models/actividad.model.request';
import { SessionStorageService } from '../../../services/session-storage.service';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { EstatusService } from '../../../services/estatus.service';


@Component({
  selector: 'app-registro-actividad',
  imports: [
    MatCardModule,
    MatIconModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    NgbTypeaheadModule,
    NgbPaginationModule],
  templateUrl: './registro-actividad.component.html',
  styleUrl: './registro-actividad.component.css'
})
export class RegistroActividadComponent implements OnInit {

  formActividad!: FormGroup;
  actividadResponse!:ActividadResponse;
  @Output() notificarRegistrado = new EventEmitter<void>();


  constructor(private sessionStorageService:SessionStorageService,
    private actividadService:ActividadService,
  ){
  }

  ngOnInit(): void {
    this.initForm();
  }


  initForm() {
    this.formActividad = new FormGroup({
      nombre: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*$/)]),
      fecha: new FormControl('', [Validators.required]),
      horaInicio: new FormControl('', [Validators.required]),
      horaFin: new FormControl('', [Validators.required]),
      comentario: new FormControl('', [Validators.required]),
    });
  }

  registrarActividad() {
    if (this.formActividad.valid) {
      const datos =this.convertirActFormToEntidad(this.formActividad);
      datos.codigoEstatus="PEN";
      this.actividadService.save(datos).subscribe({
        next:(response:ActividadResponse)=>{
          this.actividadResponse=response;
          this.notificarRegistrado.emit();
          this.showAlertSuccess("Registro Exitoso");
          console.log("DATA ACTIVIDAD: "+JSON.stringify(response));
        },error:(err)=>{
          console.log("DATA ACTIVIDAD: "+JSON.stringify(err));
          this.showMessageError("Hubo un error al registrar la actividad");
        }
      });
      console.log("DATOS: " + JSON.stringify(datos));
    }
  }

  convertirActFormToEntidad(form:FormGroup):ActividadRequest{
    const datos:ActividadRequest=form.value;
    datos.username=this.sessionStorageService.getUsernameInLocalStorage()??'';
    return datos;
  }

    showMessageError(mensaje: string): Promise<SweetAlertResult> {
      return Swal.fire({
        icon: "error",
        text: mensaje,
        showCancelButton: false,
        confirmButtonText: 'Aceptar',
        customClass: {
          confirmButton: 'sweet-alert-custom-confirm-button'
        }
      });
    }

    showAlertSuccess(mensaje: string) {
      Swal.fire({
        title: "sucess",
        icon: "success",
        text: mensaje,
        showCancelButton: false,
        confirmButtonText: "Aceptar",
      });
    }

}
