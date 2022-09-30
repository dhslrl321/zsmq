import TablePresenter from "../presenter";

import {useEffect, useState} from "react";
import {getQueueDescribeAPI} from "../../../api-service/query-queue-service";

const TableContainer = () => {

  const [queues, setQueues] = useState([]);
  const [value, setValue] = useState(1);

  useEffect(() => {
    pollingQueue();
  }, [value])

  const pollingQueue = async () => {
    const {queueTotalSize, detail} = await getQueueDescribeAPI();
    setQueues(detail);
    setTimeout(() => setValue(value + 1), 1000);
  }

  return (
    <>
      <TablePresenter queues={queues}/>
    </>
  );
}

export default TableContainer;