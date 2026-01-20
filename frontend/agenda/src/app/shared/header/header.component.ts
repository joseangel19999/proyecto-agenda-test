import { Component, OnInit } from '@angular/core';
import { ProfileInfoComponent } from './profile-info/profile-info.component';
import { MatIconModule } from '@angular/material/icon';
import { SidenavService } from '../../services/sidenav.service';
import { AuthService } from '../../services/auth.service';
import { SessionStorageService } from '../../services/session-storage.service';

@Component({
  selector: 'app-header',
  imports: [ProfileInfoComponent, MatIconModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  correo!: string;
  nombre: string;

  constructor(
    private sidenavService: SidenavService,
    private sessionStorageService: SessionStorageService,
  ) {
    this.correo = '';
    this.nombre = '';
  }

  ngOnInit(): void {
    this.extraerNombreAndCorreo();
  }

  toggleSidenav(): void {
    this.sidenavService.toggle();
  }

  extraerNombreAndCorreo() {
    this.correo = this.sessionStorageService.getItem('USERNAME') ?? '';
    this.nombre = this.sessionStorageService.getItem('NOMBRE') ?? '';
  }
}
