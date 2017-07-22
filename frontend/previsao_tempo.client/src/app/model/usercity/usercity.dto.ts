import { CityDTO } from './../city/city.dto';
import { UserDTO } from './../user/user.dto';
import {BaseDTO} from "../base/base.dto";

/**
 * Representa uma cidade do usuário na aplicação.
 */
export class UserCityDTO extends BaseDTO {

  user: UserDTO = null;
  city: CityDTO = null;
}