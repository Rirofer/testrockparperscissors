export class Property {
  readonly required: boolean;
  readonly prompt: string;
  readonly name: string;
  readonly options: Array<string>;
  readonly type: string;
  private _value: any;

  constructor(required: boolean, prompt: string, name: string, options: Array<string>, type: string) {
    this.required = required;
    this.prompt = prompt;
    this.name = name;
    this.options = options;
    this.type = type;
  }

  public get value(): any {
    return this._value;
  }

  public set value(value: any) {
    this._value = value;
  }

}
