import {Property} from "./property";
import {HttpMethod} from "./httpmethod";

export class Command {
  readonly key: string;
  readonly method: HttpMethod;
  readonly target: string;
  readonly properties: Array<Property>;

  constructor(key: string, method: HttpMethod, target: string, properties: Array<Property>) {
    this.key = key;
    this.target = target;
    this.method = method;
    this.properties = properties;
  }

}
