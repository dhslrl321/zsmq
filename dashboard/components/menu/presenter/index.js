import * as React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Divider from '@mui/material/Divider';
import Drawer from '@mui/material/Drawer';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import MenuIcon from '@mui/icons-material/Menu';
import HomeIcon from '@mui/icons-material/Home';
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import Toolbar from '@mui/material/Toolbar';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import Typography from '@mui/material/Typography';

import CommandQueueModal from '../../command-dialog/presenter';
import { useState } from 'react';
import { createQueueAPI, deleteQueueAPI } from '../../../api-service/command-queue-service';
import { ERROR_BAD_REQUEST, ERROR_NOT_FOUND } from '../../../commons/constants';
import { regexNumberAndEnglishAndHyphen } from '../../../commons/validator';

const drawerWidth = 240;

function ResponsiveDrawer({ window, children }) {
  const [mobileOpen, setMobileOpen] = useState(false);

  // create queue modal
  const [createQModalOpen, setCreateQModalOpen] = useState(false);
  const [createQModalInput, setCreateQModalInput] = useState('');
  const handleCreateQModalOpen = () => {
    setCreateQModalOpen(true);
  };
  const handleCreateQModalClose = () => {
    setCreateQModalInput('');
    setCreateQModalOpen(false);
  };
  const handleChangeCreateQModalInput = (e) => {
    const { value } = e.target;
    setCreateQModalInput(value);
  };
  const handleOnClickCreate = async () => {
    if (!regexNumberAndEnglishAndHyphen(createQModalInput)) {
      alert('❌ Error! \nQueue Name must be present and under 40 length, \nTry Again!!');
      return;
    }
    const res = await createQueueAPI(createQModalInput);
    setCreateQModalInput('');
    handleCreateQModalClose();
  };

  // delete queue modal
  const [deleteQueueModalOpen, setDeleteQueueModalOpen] = useState(false);
  const [deleteQModalInput, setDeleteQModalInput] = useState('');
  const handleDeleteQModalOpen = () => {
    setDeleteQueueModalOpen(true);
  };
  const handleDeleteQModalClose = () => {
    setDeleteQModalInput('');
    setDeleteQueueModalOpen(false);
  };
  const handleChangeDeleteQModalInput = (e) => {
    const { value } = e.target;
    setDeleteQModalInput(value);
  };
  const handleOnClickDelete = async () => {
    if (!regexNumberAndEnglishAndHyphen(deleteQModalInput)) {
      alert('❌ Error! \nQueue Name must be present and under 40 length, \nTry Again!!');
      return;
    }
    const res = await deleteQueueAPI(deleteQModalInput);
    if (res === ERROR_NOT_FOUND) {
      alert(`❌ Error! \n[${deleteQModalInput}] Queue Not Found \nTry Again!!`);
    }
    setDeleteQModalInput('');
    handleDeleteQModalClose();
  };

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const drawer = (
    <div>
      <CommandQueueModal
        open={createQModalOpen}
        handleClickOpen={handleCreateQModalOpen}
        handleClose={handleCreateQModalClose}
        title="Create Queue"
        content="Enter the queue name to create. Only A-Z,a-z, -, number"
        text={createQModalInput}
        onChangeInput={handleChangeCreateQModalInput}
        onClickSubmit={handleOnClickCreate}
      />

      <CommandQueueModal
        open={deleteQueueModalOpen}
        handleClickOpen={handleDeleteQModalOpen}
        handleClose={handleDeleteQModalClose}
        title="Delete Queue"
        content="Enter the queue name to delete. Only A-Z,a-z, -, number"
        text={deleteQModalInput}
        onChangeInput={handleChangeDeleteQModalInput}
        onClickSubmit={handleOnClickDelete}
      />
      <Divider />
      <List>
        <ListItem key="Home" disablePadding>
          <ListItemButton>
            <ListItemIcon>
              <HomeIcon />
            </ListItemIcon>
            <ListItemText primary="Home" />
          </ListItemButton>
        </ListItem>
      </List>
      <Divider />
      <List>
        <ListItem key={'Create Queue'} disablePadding onClick={handleCreateQModalOpen}>
          <ListItemButton>
            <ListItemIcon>
              <AddCircleOutlineIcon />
            </ListItemIcon>
            <ListItemText primary={'Create Queue'} />
          </ListItemButton>
        </ListItem>
        <ListItem key={'Delete Queue'} disablePadding onClick={handleDeleteQModalOpen}>
          <ListItemButton>
            <ListItemIcon>
              <DeleteForeverIcon />
            </ListItemIcon>
            <ListItemText primary={'Delete Queue'} />
          </ListItemButton>
        </ListItem>
      </List>
    </div>
  );

  const container = window !== undefined ? () => window().document.body : undefined;

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
        }}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: 'none' } }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" noWrap component="div">
            Zola Simple Message Queue
          </Typography>
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Drawer
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: 'block', sm: 'none' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: { xs: 'none', sm: 'block' },
            '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{ flexGrow: 1, p: 3, width: { sm: `calc(100% - ${drawerWidth}px)` } }}
      >
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
}

ResponsiveDrawer.propTypes = {
  window: PropTypes.func,
};

export default ResponsiveDrawer;
