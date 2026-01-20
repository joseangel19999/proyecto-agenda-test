import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatExpansionModule } from '@angular/material/expansion';
import { CommonModule } from '@angular/common';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { map, Observable, shareReplay } from 'rxjs';
import { SidenavService } from '../../services/sidenav.service';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MenuComponent } from '../../shared/menu/menu.component';

@Component({
  selector: 'app-default',
  imports: [
    RouterModule
  ],
  templateUrl: './default.component.html',
  styleUrl: './default.component.css'
})
export class DefaultComponent{

}
