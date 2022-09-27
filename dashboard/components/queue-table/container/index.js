import TablePresenter from "../presenter";

import rows from "./data";

const TableContainer = () => {
  return (
      <TablePresenter rows={rows}/>
  );
}

export default TableContainer;