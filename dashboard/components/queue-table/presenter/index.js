import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import * as S from './styles';

export default function BasicTable({ queues }) {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Queue Name</TableCell>
            <TableCell align="right">messagesAvailable</TableCell>
            <TableCell align="right">createdAt</TableCell>
            <TableCell align="right">status</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {queues.map((queue) => (
            <TableRow key={queue.name} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell component="th" scope="row">
                {queue.name}
              </TableCell>
              <TableCell align="right">{queue.messagesAvailable}</TableCell>
              <TableCell align="right">{queue.created}</TableCell>
              <TableCell align="right">
                <S.DotContainer>
                  {/*{queue.health ? <S.GreenDot /> : <S.RedDot />}*/}
                  <S.GreenDot />
                </S.DotContainer>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
