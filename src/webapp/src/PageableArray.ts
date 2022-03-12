import { SortValue } from "@smui/data-table";

export class PageableArray<E> extends Array<E> {
  public getPage(
    limit: number = 0,
    page: number = 0,
    sortValue: SortValue = SortValue.NONE,
    sortBy?: keyof E
  ) {
    console.log(sortValue);
    let list;
    if (sortBy !== undefined && sortValue !== SortValue.NONE) {
      list = this.sort((a, b) => (a[sortBy] < b[sortBy] ? 1 : -1));
    } else {
      list = this;
    }
    if (sortValue === SortValue.ASCENDING) list = list.reverse();

    if (limit === 0) return list;
    return list.slice(page * limit, page * limit + limit);
  }
}
