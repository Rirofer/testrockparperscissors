import { Command } from "./command";
import { Link } from "./link";

export abstract class Resource {

    private readonly _links: Map<string, Link>;
    private readonly _templates: Map<string, Command>;

    protected  constructor(_links: Map<string, Link>, _templates: Map<string, Command>) {
        this._links = _links;
        this._templates = _templates;
    }

    public getLink(rel: string): Link | undefined {
        return this._links.get(rel);
    }

    public getCommand(key: string): Command | undefined {
        return this._templates.get(key);
    }

}
