import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {

    const idToken = window.sessionStorage.getItem("token");
    const subject = window.sessionStorage.getItem("subject");
    console.log('Checking if auth.service is getting the right token', idToken)

    if (idToken && subject) {
      const cloned = req.clone({
        headers: req.headers.set("Authorization", "Bearer " + idToken)
                            .set("subject", subject)

      });

      return next.handle(cloned);
    }
    else {
      return next.handle(req);
    }
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
