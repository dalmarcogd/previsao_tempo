import {BaseDTO} from "../base/base.dto";

/**
 * Representa um token de autenticação do usuario.
 */
export class TaskDTO extends BaseDTO {

  title: string = null;
  description: string = null;
  taskStatus: string = "OPENED";
  dateStart: Date = null;
  dateUpdate: Date = null;
  dateRemove: Date = null;
  dateEnd: Date = null;
}