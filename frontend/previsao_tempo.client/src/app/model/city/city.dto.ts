import { StateDTO } from './../state/state.dto';
import {BaseDTO} from "../base/base.dto";

/**
 * Representa uma cidade da aplicação.
 */
export class CityDTO extends BaseDTO {

  name: string = null;
  state: StateDTO = null;
}