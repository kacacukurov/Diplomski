import {regionDTO} from "./regionDTO";
import {grapeDTO} from "./grapeDTO";
import {wineryDTO} from "./wineryDTO";

export class wineDTO{
  id: number;
  wineName: string;
  wineBody: string;
  wineColor: string;
  wineSugar: string;
  wineFlavor: string;
  regionDTO: regionDTO;
  grapeDTOS: Array<grapeDTO>;
  subclassOfWine: string;
  wineryDTO: wineryDTO;
  isSuperClass: boolean;
}
