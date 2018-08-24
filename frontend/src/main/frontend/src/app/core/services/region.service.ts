import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {regionDTO} from "../../shared/models/regionDTO";
import {newRegionDTO} from "../../shared/models/newRegionDTO";

@Injectable()
export class RegionService {

  private readonly urlBase = '/api/region';

  constructor(private http: HttpClient) { }

  addNewRegion(newRegionDTO: newRegionDTO): Observable<void> {
    return this.http.post<newRegionDTO>(`${this.urlBase}/new`, newRegionDTO).catch(this.handleErrors);
  }

  changeRegion(regionDTO: regionDTO): Observable<void> {
    return this.http.put<regionDTO>(`${this.urlBase}/change`, regionDTO).catch(this.handleErrors);
  }

  allRegions():Observable<Array<regionDTO>> {
    return this.http.get<Array<regionDTO>>(`${this.urlBase}/all`).catch(this.handleErrors);
  }

  deleteRegion(id: number): Observable<void> {
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
