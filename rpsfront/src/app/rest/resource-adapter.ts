import {Link} from "./link";
import {Command} from "./command";

export abstract class ResourceAdapter {

  abstract toLinks(resource: any) : Map<string, Link>;

  abstract toCommands(resource: any) : Map<string, Command>;

  abstract getResources(collectionResource: any, rel: string): Array<any>;
}
