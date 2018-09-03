import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BadRequestError} from "../../shared/errors/bad-request-error";
import {ForbiddenError} from "../../shared/errors/forbidden-error";
import {NotFoundError} from "../../shared/errors/not-found-error";
import {ConflictError} from "../../shared/errors/conflict-error";
import {AppError} from "../../shared/errors/app-error";
import {grapeListDTO} from "../../shared/models/grapeListDTO";
import {wineDTO} from "../../shared/models/wineDTO";
import {missingGrapesDTO} from "../../shared/models/missingGrapesDTO";
import {grapeDTO} from "../../shared/models/grapeDTO";
import {filterDTO} from "../../shared/models/filterDTO";

@Injectable()
export class DroolsService {

  private readonly urlBase = '/api/drools';

  constructor(private http: HttpClient) { }

  getPossibleWinesFromGrapes(grapeListDTO: grapeListDTO): Observable<Array<wineDTO>> {
    return this.http.post<grapeListDTO>(`${this.urlBase}/findWines`, grapeListDTO).catch(this.handleErrors);
  }

  getMissingGrapesForWine(missingGrapeDTO: missingGrapesDTO): Observable<Array<grapeDTO>> {
    return this.http.post<missingGrapesDTO>(`${this.urlBase}/findMissingGrapes`, missingGrapeDTO).catch(this.handleErrors);
  }

  getPotentialWines(grapeListDTO: grapeListDTO): Observable<Array<missingGrapesDTO>> {
    return this.http.post<grapeListDTO>(`${this.urlBase}/findPotentialWineAndGrapes`, grapeListDTO).catch(this.handleErrors);
  }

  filterWines(filterDTO: filterDTO): Observable<Array<wineDTO>> {
    return this.http.post<filterDTO>(`${this.urlBase}/filterWines`, filterDTO).catch(this.handleErrors);
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
