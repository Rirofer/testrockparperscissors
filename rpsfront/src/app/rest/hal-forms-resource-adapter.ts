import {ResourceAdapter} from "./resource-adapter";
import {Link} from "./link";
import {Command} from "./command";
import {Property} from "./property";
import {Injectable} from "@angular/core";

@Injectable({
    providedIn: "root"
  })
export class HalFormsResourceAdapter extends ResourceAdapter {

  private readonly LINKS = '_links';
  private readonly TEMPLATES = '_templates';
  private readonly EMBEDDED = '_embedded';

  toLinks(resource: any): Map<string, Link> {
    const links = new Map<string, Link>();
    if (resource.hasOwnProperty(this.LINKS)) {
      Object.entries(resource[this.LINKS]).forEach(e => {
        const halLink: any = e[1];
        const link = new Link(e[0], halLink['href']);
        links.set(link.rel, link);
      });
    }
    return links;
  }

  toCommands(resource: any): Map<string, Command> {
    const commands = new Map<string, Command>();
    if (resource.hasOwnProperty(this.TEMPLATES)) {
      Object.entries(resource[this.TEMPLATES]).forEach(e => {
        const key = e[0];
        if (key !== 'default') {
          commands.set(e[0], this.toCommand(e[0], e[1]));
        }
      });
    }
    return commands;
  }

  private toCommand(key: string, template: any): Command {
    return new Command(key, template['method'], template['target'], this.toProperties(template['properties']));
  }

  private toProperties(properties: Array<any>): Array<Property> {
    return properties.map(v => this.toProperty(v));
  }

  private toProperty(property: any): Property {
    const options = this.toOptions(property['options']);
    return new Property(property['required'], property['prompt'], property['name'],
      options, property['type']);
  }

  private toOptions(options: any): Array<string> {
    const inline = new Array<string>();
    if (typeof options != 'undefined' && options.hasOwnProperty('inline')) {
      options['inline'].forEach((o: string) => inline.push(o));
    }
    return inline;
  }

  getResources(collectionResource: any, rel: string): Array<any> {
    if (collectionResource.hasOwnProperty(this.EMBEDDED)
      && collectionResource[this.EMBEDDED].hasOwnProperty(rel)){
      return collectionResource[this.EMBEDDED][rel];
    }
    return [];
  }

}
