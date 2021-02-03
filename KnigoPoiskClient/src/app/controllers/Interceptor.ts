import {
  Injectable, Input
} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpErrorResponse,
  HttpResponse
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from "rxjs/operators";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ModalWindowComponent} from "../modal-window/modal-window.component";

@Injectable()
export class Interceptor implements HttpInterceptor {
  private message: string

  constructor(private modalService: NgbModal) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any> {
    const jwt = localStorage.getItem('auth_token');
    if (jwt) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${jwt}`
        }
      });
    }
    return next.handle(request).pipe(
      tap(
        (event) => {
          if (event instanceof HttpResponse)
            console.log(event);
        },
        (err) => {
          if (err instanceof HttpErrorResponse) {
            console.log(err);
            if (err.status == 401) {
              this.message = err.error.message;
            }
            if (err.status == 500) {
              this.message = "Поле заполненно не правильно ";
            }
            this.open();
          }
        }
      )
    )
  }

  open() {
    const modalRef = this.modalService.open(ModalWindowComponent);
    modalRef.componentInstance.name = this.message;
  }
}