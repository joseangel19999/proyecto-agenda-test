import { Component, inject, ViewChild } from '@angular/core';
import { MenuComponent } from '../../shared/menu/menu.component';
import { RouterModule } from '@angular/router';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { SidenavService } from '../../services/sidenav.service';
import { map, Observable, shareReplay } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  imports: [
    RouterModule,
    MatExpansionModule,
    CommonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatExpansionModule,
    MatListModule,
    RouterModule,
    MenuComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  private breakpointObserver = inject(BreakpointObserver);
  @ViewChild('drawer')
  drawer!: MatSidenav;


  constructor(private sidenavService: SidenavService) {
  }
  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  ngOnInit(): void {
    this.isHandset$.subscribe(isHandset => {
      if (this.drawer) {
        this.sidenavService.setSidenav(this.drawer);
      }
    });
  }
}
