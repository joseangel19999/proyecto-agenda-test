import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private readonly CLAVE_KEY_USER: string = "USERNAME";
  private readonly TOKEN_KEY_USER: string = "TOKEN";
  private readonly TOKEN_KEY_NOMBRE: string = "NOMBRE";


  constructor(@Inject(PLATFORM_ID) private platformId: Object) { }

  saveUsernameAndTokenUInLocalStorage(username: string, token: string,nombre:string) {
    if (isPlatformBrowser(this.platformId)) {
      this.setItem(this.CLAVE_KEY_USER, username);
      this.setItem(this.TOKEN_KEY_USER, token);
      this.setItem(this.TOKEN_KEY_NOMBRE,nombre);
    }
  }

  getUsernameInLocalStorage(): string | null {
    return this.getItem(this.CLAVE_KEY_USER);
  }

  getTokenInSessionStorage(): string | null {
    return this.getItem(this.TOKEN_KEY_USER);
  }

  setItem(key: string, value: string): void {
    if (isPlatformBrowser(this.platformId)) {
      try {
        sessionStorage.setItem(key, value);
      } catch (e) {
        console.error('Error al guardar en sessionStorage:', e);
      }
    }
  }

  getItem(key: string): string | null {
    if (isPlatformBrowser(this.platformId)) {
      try {
        return sessionStorage.getItem(key);
      } catch (e) {
        console.error('Error al obtener de sessionStorage:', e);
        return null;
      }
    }
    return null;
  }
}
