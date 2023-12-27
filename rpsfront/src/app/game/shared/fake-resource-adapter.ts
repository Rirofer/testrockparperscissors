import {Command, ResourceAdapter} from "../../rest";
import {Link} from "../../rest/link";

export class FakeResourceAdapter implements  ResourceAdapter {
  getResources(collectionResource: any, rel: string): Array<any> {
    return [];
  }

  toCommands(resource: any): Map<string, Command> {
    return new Map();
  }

  toLinks(resource: any): Map<string, Link> {
    return new Map();
  }

}
