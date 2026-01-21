import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RouterModule } from '@angular/router';
import { NgbPaginationModule, NgbTypeaheadModule } from '@ng-bootstrap/ng-bootstrap';
import { ActividadResponse } from '../../../models/actividad.model.response';
import { EstatusService } from '../../../services/estatus.service';
import { EstatusResponse } from '../../../models/estatus.response.model';
import { ActividadService } from '../../../services/actividad.service';
import Swal, { SweetAlertResult } from 'sweetalert2';


@Component({
  selector: 'app-listar-actividad',
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
    FontAwesomeModule,
    NgbPaginationModule],
  templateUrl: './listar-actividad.component.html',
  styleUrl: './listar-actividad.component.css'
})
export class ListarActividadComponent implements OnInit, OnChanges {

  hide = true;
  page = 1;
  pageSize = 10;
  collectionSize = 0;
  listaEstatus: EstatusResponse[] = [];
  @Input() actividades: ActividadResponse[] = [];
  @Output() notificarRegistrado = new EventEmitter<void>();
  listaFilterActividades: ActividadResponse[] = [];

  constructor(
    private estatusService: EstatusService,
    private library: FaIconLibrary,
    private actividadService: ActividadService
  ) {
    this.library.addIconPacks(fas);
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['actividades']) {
      this.listaFilterActividades = this.actividades;
      this.collectionSize = this.actividades.length;
      this.refreshCollection();
    }
  }
  ngOnInit(): void {
    this.listarEstatus();
  }

  listarEstatus() {
    this.estatusService.listarEstatus().subscribe({
      next: (response: EstatusResponse[]) => {
        this.listaEstatus = response;
      }, error: (err) => {
        console.log("error: " + JSON.stringify(err));
      }
    })
  }
  async eliminar(id: number) {
    const result = await this.confirmarEliminar();
    if (result.isConfirmed) {
      console.log("ID ELIMINAR: " + JSON.stringify(id));
      this.actividadService.delete(id).subscribe({
        next: () => {
          this.notificarRegistrado.emit();
          this.showAlertSuccess("Eliminacion exitosa");
        }, error: (err) => {
          this.showMessageError("Hubo un error al eliminar");
        }
      });
    }
  }
  refreshCollection() {
    this.listaFilterActividades = this.actividades.map((actividad, i) => ({ actividad: i + 1, ...actividad })).slice(
      (this.page - 1) * this.pageSize,
      (this.page - 1) * this.pageSize + this.pageSize,
    );
  }

  confirmarEliminar(): Promise<SweetAlertResult> {
    return Swal.fire({
      title: "¿Estas seguro de eliminar?",
      icon: "warning",
      showCancelButton: true,
      cancelButtonColor: "#000000",
      confirmButtonText: "Eliminar",
      cancelButtonText: 'Cancelar',
      customClass: {
        cancelButton: 'sweet-alert-cancel-white-text'
      }
    });
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
