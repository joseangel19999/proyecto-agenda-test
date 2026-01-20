import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-profile-info',
  imports: [MatIconModule],
  templateUrl: './profile-info.component.html',
  styleUrl: './profile-info.component.css'
})
export class ProfileInfoComponent {

  @Input() correo: string;
  @Input() nombre: string;

  constructor() {
    this.correo = '';
    this.nombre = '';
  }
}
