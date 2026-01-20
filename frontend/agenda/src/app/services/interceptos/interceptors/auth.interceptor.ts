import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../../auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { SessionStorageService } from '../../session-storage.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService=inject(AuthService);
  const sessionStorageService=inject(SessionStorageService);
  const router = inject(Router);
  let authToken =sessionStorageService.getTokenInSessionStorage();
  let modifiedReq = req;
  if (authToken) {
    modifiedReq = req.clone({
      setHeaders: {
        Authorization:` Bearer ${authToken}`
      }
    });
  }
return next(modifiedReq);
};
