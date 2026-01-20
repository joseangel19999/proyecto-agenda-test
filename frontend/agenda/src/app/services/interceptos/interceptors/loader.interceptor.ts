import { HttpInterceptorFn } from '@angular/common/http';
import { hideLoader, showLoader } from '../../load/loading.function';
import { finalize } from 'rxjs';

export const loaderInterceptor: HttpInterceptorFn = (req, next) => {
  showLoader();
  return next(req).pipe(
    finalize(() => {
      hideLoader();
    })
  );
};
