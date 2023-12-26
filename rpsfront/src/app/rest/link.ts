export class Link {
  readonly rel: string;
  readonly href: string;

  constructor(rel: string, href: string) {
    this.rel = rel;
    this.href = href;
  }

}
