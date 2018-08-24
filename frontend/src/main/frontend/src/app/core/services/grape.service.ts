import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {grapeDTO} from "../../shared/models/grapeDTO";

@Injectable()
export class GrapeService {

  private readonly urlBase = '/api/grape';

  constructor(private http: HttpClient) { }

  addNewGrape(grapeName: string): Observable<void> {
    return this.http.post<string>(`${this.urlBase}/new`, grapeName).catch(this.handleErrors);
  }

  allGrapes():Observable<Array<grapeDTO>> {
    return this.http.get<Array<grapeDTO>>(`${this.urlBase}/all`).catch(this.handleErrors);
  }

  deleteGrape(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/?id=${id}`).catch(this.handleErrors);
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
