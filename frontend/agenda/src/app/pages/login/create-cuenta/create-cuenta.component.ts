import { Component, Inject, OnInit, signal } from '@angular/core';
import { LoginComponent } from '../login.component';
import { MatDialogClose, MatDialogRef } from '@angular/material/dialog';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatStepperModule } from '@angular/material/stepper';
import { CommonModule } from '@angular/common';
import { CuentaService } from '../../../services/cuenta.service';
import { CuentaRequest } from '../../../models/cuenta.model.request';
import { CuentaResponse } from '../../../models/cuenta.model.response';
import Swal, { SweetAlertResult } from 'sweetalert2';

export const matchPasswordsValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password');
  const passwordConfirm = control.get('passwordConfirm');
  if (!password || !passwordConfirm) {
    return null;
  }
  if (passwordConfirm.value && password.value !== passwordConfirm.value) {
    passwordConfirm.setErrors({ ...passwordConfirm.errors, passwordsMismatch: true });
    return { passwordsMismatch: true };
  }
  if (passwordConfirm.hasError('passwordsMismatch')) {
    const { passwordsMismatch, ...rest } = passwordConfirm.errors || {};
    passwordConfirm.setErrors(Object.keys(rest).length ? rest : null);
  }
  return null;
};
@Component({
  selector: 'app-create-cuenta',
  imports: [MatButtonModule,
    MatDialogClose,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    MatStepperModule,
    CommonModule],
  templateUrl: './create-cuenta.component.html',
  styleUrl: './create-cuenta.component.css'
})
export class CreateCuentaComponent implements OnInit {

   hide = signal(true);
  hidePassword = signal(true);
  hidePasswordConfirm = signal(true);
  cuentaFom!: FormGroup;
  cuentaCreado!: CuentaResponse;

  constructor(public dialogRef: MatDialogRef<LoginComponent>, private cuentaService: CuentaService) {
  }

  ngOnInit(): void {
    this.inicializacionCuentaForm();
  }

  inicializacionCuentaForm() {
    this.cuentaFom = new FormGroup({
      nombre: new FormControl('', Validators.required),
      apellido: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8)
      ]),
      passwordConfirm: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
      ])
    }, {
      validators: [matchPasswordsValidator]
    });
  }

  clickEventPassword(event: MouseEvent) {
    this.hidePassword.set(!this.hidePassword());
    event.stopPropagation();
  }

  clickEventPasswordConfirm(event: MouseEvent) {
    this.hidePasswordConfirm.set(!this.hidePasswordConfirm());
    event.stopPropagation();
  }
  togglePasswordVisibility(event: MouseEvent, type: 'password' | 'confirm') {
    event.preventDefault();
    if (type === 'password') {
      this.hidePassword.update(current => !current);
    } else {
      this.hidePasswordConfirm.update(current => !current);
    }
  }

  get passwordControl() {
    return this.cuentaFom.get('password') as FormControl;
  }

  get passwordConfirmControl() {
    return this.cuentaFom.get('passwordConfirm') as FormControl;
  }

  registrar() {
    if (this.cuentaFom.valid) {
      const cuenta = this.formularioToEntidad(this.cuentaFom);
      this.cuentaService.save(cuenta).subscribe({
        next: (response: CuentaResponse) => {
          if (response != null && response != undefined) {
            this.cuentaCreado = response;
            this.showAlertSuccess("Se creo la cuenta correctamente");
            this.dialogRef.close();
          }
        }, error: (err) => {
          this.showMessageError("HUbo error al crear la cuenta");
        }
      });

    }
  }

  formularioToEntidad(formCuenta: FormGroup): CuentaRequest {
    const datos: CuentaRequest = formCuenta.value;
    console.log(datos);
    return datos;
  }

  showErrorRegistro() {
    Swal.fire({
      title: "Ocurrio un error al agregar.",
      icon: "error",
      showCancelButton: false,
      confirmButtonText: "Aceptar",
    });
  }

   mostrarPassword(): void {
    this.hide = signal(false);
  }
  ocultarPassword(): void {
    this.hide = signal(true);
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
