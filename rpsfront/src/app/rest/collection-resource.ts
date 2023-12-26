import { Command } from "./command";
import { Link } from "./link";
import { Resource } from "./resource";

export class CollectionResource<T extends Resource> extends Resource {

    private readonly _resources: Array<T>;

    constructor(resources: Array<T>, links: Map<string, Link>, commands: Map<string, Command>) {
        super(links, commands);
        this._resources = resources;
    }

    public get resources(): Array<T> {
        return this._resources;
    }

}
