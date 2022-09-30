import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function Modal({open, handleClickOpen, handleClose, title, content, text, onChangeInput, onClickSubmit}) {
  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Open form dialog
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{title}</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {content}
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="QUEUE-NAME"
            type="text"
            fullWidth
            variant="standard"
            value={text}
            onChange={onChangeInput}
          />
        </DialogContent>
        <DialogActions>
          <Button style={{color: 'gray'}} onClick={handleClose}>Cancel</Button>
          <Button style={{color: 'green'}} onClick={onClickSubmit}>{title}</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
