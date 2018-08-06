import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {registrationDTO} from "../../shared/models/registrationDTO";
import {accountDTO} from "../../shared/models/accountDTO";

@Injectable()
export class UserService {
  private readonly urlBase = '/api/user';

  constructor(private http: HttpClient) { }

  registerAdmin(registration: registrationDTO): Observable<void> {
    return this.http.post<registrationDTO>(`${this.urlBase}/admin`, registration).catch(this.handleErrors);
  }

  changeAccount(registration: registrationDTO): Observable<void> {
    return this.http.put<registrationDTO>(`${this.urlBase}/changeAccount`, registration).catch(this.handleErrors);
  }

  allAdmins(): Observable<Array<accountDTO>> {
    return this.http.get<Array<accountDTO>>(`${this.urlBase}/allAccounts/?typeName=ADMIN`).catch(this.handleErrors);
  }

  deleteAccount(username: string): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/?username=${username}`).catch(this.handleErrors);
  }

  protected handleErrors(response: Response) {
    if(response.status === 400)
      return Observable.throw(new BadRequestError());
    else if(response.status === 403)
      return Observable.throw(new ForbiddenError());
    else if(response.status === 404)
      return Observable.throw(new NotFoundError());
    else if(response.status === 409)
      return Observable.throw(new ConflictError());
    return Observable.throw(new AppError(response));
  }
}
