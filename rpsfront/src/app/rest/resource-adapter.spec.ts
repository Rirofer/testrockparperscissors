import {ResourceAdapter} from "./resource-adapter";
import {HalFormsResourceAdapter} from "./hal-forms-resource-adapter";
import {HttpMethod} from "./httpmethod";

const EMBEDDED_RES = {
  "_embedded": {
    "games": [
      {
        "id": "5ae2e358-95bb-4ff1-b17c-7e5c43d78a44"
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/games"
    }
  },
  "_templates": {
    "default": {
      "method": "PUT",
      "properties": []
    },
    "start-game": {
      "method": "POST",
      "properties": []
    }
  }
};

const RESOURCE_JSON = {
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/games/8439c86b-4d72-460b-85ef-2303a2e76065"
    }
  },
  "_templates": {
    "default": {
      "method": "PUT",
      "properties": []
    },
    "play-random-round-command": {
      "method": "POST",
      "properties": [
        {
          "name": "playerOneMove",
          "readOnly": true,
          "required": true,
          "options": {
            "inline": [
              "ROCK",
              "PAPER",
              "SCISSORS"
            ]
          }
        }
      ],
      "target": "http://localhost:8080/api/games/8439c86b-4d72-460b-85ef-2303a2e76065/play/round/random/command"
    }
  }
};
describe('ResourceAdapter tests', () => {
  let resourceAdapter: ResourceAdapter;

  beforeEach(() => {
    resourceAdapter = new HalFormsResourceAdapter();
  });

  it('#toLinks should get correct links from resource', () => {
    const links = resourceAdapter.toLinks(RESOURCE_JSON);

    expect(links.size).toEqual(1);
    const self = links.get('self');
    expect(self).toBeDefined();
    expect(self?.rel).toEqual('self');
    expect(self?.href).toEqual('http://localhost:8080/api/games/8439c86b-4d72-460b-85ef-2303a2e76065');
  });

  it('#toCommands should get correct commands from resource', () => {
    const commands = resourceAdapter.toCommands(RESOURCE_JSON);

    expect(commands.size).toEqual(1);
    const command = commands.get('play-random-round-command');
    expect(command).toBeDefined();
    expect(command?.method).toEqual(HttpMethod.POST);
    expect(command?.target).toEqual('http://localhost:8080/api/games/8439c86b-4d72-460b-85ef-2303a2e76065/play/round/random/command');
    expect(command?.properties.length).toEqual(1);
    const prop = command?.properties[0];
    expect(prop?.name).toEqual('playerOneMove');
    expect(prop?.required).toBeTrue();
    expect(prop?.options).toEqual(['ROCK', 'PAPER', 'SCISSORS']);

  });

  it('#getResources should return collection resource elements', () => {
    const resources = resourceAdapter.getResources(EMBEDDED_RES, 'games');

    expect(resources.length).toEqual(1);

  });
});
