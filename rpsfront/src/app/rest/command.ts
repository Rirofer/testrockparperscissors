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

  getProperty(propName: string): Property | undefined {
    if(typeof this.properties != 'undefined') {
      return this.properties.find(p => p.name == propName);
    }
    return;
  }

  getPropertyOptions(propName: string): Array<string> {
    const options = new Array<string>();
    const property = this.getProperty(propName);
    if(typeof property != 'undefined' && typeof property?.options != 'undefined') {
      return property?.options;
    }
    return options;
  }
}
