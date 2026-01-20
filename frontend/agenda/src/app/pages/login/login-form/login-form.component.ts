import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { CreateCuentaComponent } from '../create-cuenta/create-cuenta.component';
import { MatDialog } from '@angular/material/dialog';
import { SessionStorageService } from '../../../services/session-storage.service';
import { LoginRequest } from '../../../models/login.model.request';
import { AuthService } from '../../../services/auth.service';
import { LoginResponse } from '../../../models/login.model.response';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, ReactiveFormsModule, CommonModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent implements OnInit {

  hide = signal(true);
  loginForm!: FormGroup;

  constructor(public dialog: MatDialog, private sessionStorageService: SessionStorageService,
    private authService: AuthService,
    private router: Router
  ) {

  }
  ngOnInit(): void {
    this.inicializarFormularioLogin();
  }

  inicializarFormularioLogin() {
    this.loginForm = new FormGroup({
      username: new FormControl('', [
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6)
      ])
    });
  }

  mostrarPassword(): void {
    this.hide = signal(false);
  }
  ocultarPassword(): void {
    this.hide = signal(true);
  }
  getControl(value: string) {
    return this.loginForm.get(value) as FormControl;
  }

  login() {
    if (this.loginForm.valid) {
      const loginRequest = this.convertirLoginFormToLOginRequest(this.loginForm);
      this.authService.autenticarUsuario(loginRequest).subscribe({
        next: (response: LoginResponse) => {
          this.sessionStorageService.saveUsernameAndTokenUInLocalStorage(response.username,response.token,response.nombre);
          this.router.navigate(['/home']);
        }, error: (err) => {
          if (err.status == '401') {
            this.showMessageError("Usuario o Password son incorrectos");
          }
        }
      });
    }
  }

  convertirLoginFormToLOginRequest(form: FormGroup): LoginRequest {
    const datos = form.value;
    return datos;
  }

  abrirDialogCuenta(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(CreateCuentaComponent, {
      width: '50vw',
      maxWidth: '400px',
      enterAnimationDuration,
      exitAnimationDuration,
      panelClass: 'custom-dialog-container'
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
}
