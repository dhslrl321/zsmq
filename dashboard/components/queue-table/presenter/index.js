import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import * as S from "./styles";

export default function BasicTable({rows}) {
  return (
    <TableContainer component={Paper}>
      <Table sx={{minWidth: 650}} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Queue Name</TableCell>
            <TableCell align="right">messagesAvailable</TableCell>
            <TableCell align="right">createdAt</TableCell>
            <TableCell align="right">status</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.name}
              sx={{'&:last-child td, &:last-child th': {border: 0}}}
            >
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.messagesAvailable}</TableCell>
              <TableCell align="right">{row.createdAt}</TableCell>
              <TableCell align="right">
                <S.DotContainer>
                  {row.health ? <S.GreenDot /> : <S.RedDot />}
                </S.DotContainer>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
