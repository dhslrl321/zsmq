function createData(name, messagesAvailable, createdAt, health) {
  return { name, messagesAvailable, createdAt, health };
}

const rows = [
  createData('ORDER-PURCHASED-CONFIRMED', 159, '2022-09-24T11:09:08', true),
  createData('CANCELED', 237, '2022-09-24T11:09:08', true),
  createData('MEMBER-JOINED', 262, '2022-09-24T11:09:08', false),
  createData('REFUNDED', 305,'2022-09-24T11:09:08', true),
  createData('LEFT', 356, '2022-09-24T11:09:08', false),
];

export default rows;