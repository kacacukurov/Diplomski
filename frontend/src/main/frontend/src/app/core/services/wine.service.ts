import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {newWineDTO} from "../../shared/models/newWineDTO";
import {wineDTO} from "../../shared/models/wineDTO";
import {wineSimilarDTO} from "../../shared/models/wineSimilarDTO";

@Injectable()
export class WineService {
  private readonly urlBase = '/api/wine';

  constructor(private http: HttpClient) { }

  addNewWine(newWineDTO: newWineDTO): Observable<void> {
    return this.http.post<newWineDTO>(`${this.urlBase}/new`, newWineDTO).catch(this.handleErrors);
  }

  changeWine(wineDTO: wineDTO): Observable<void> {
    return this.http.put<wineDTO>(`${this.urlBase}/change`, wineDTO).catch(this.handleErrors);
  }

  allWines():Observable<Array<wineDTO>> {
    return this.http.get<Array<wineDTO>>(`${this.urlBase}/all`).catch(this.handleErrors);
  }

  deleteWine(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/?id=${id}`).catch(this.handleErrors);
  }

  similarWine(title: string): Observable<Array<wineSimilarDTO>>{
    return this.http.get<Array<wineSimilarDTO>>(`${this.urlBase}/similar/${title}`).catch(this.handleErrors);
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
