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

import DeleteQueueModal from "../../dialog";

const drawerWidth = 240;

function ResponsiveDrawer({window, children}) {
  const [mobileOpen, setMobileOpen] = React.useState(false);
  const [deleteQueueModalOpen, setDeleteQueueModalOpen] = React.useState(false);
  const [createQueueModalOpen, setCreateQueueModalOpen] = React.useState(false);

  const handleCreateQueueModalOpen = () => {
    setCreateQueueModalOpen(true);
  };

  const handleCreateQueueModalClose = () => {
    setCreateQueueModalOpen(false);
  };

  const handleDeleteQueueModalOpen = () => {
    setDeleteQueueModalOpen(true);
  };

  const handleDeleteQueueModalClose = () => {
    setDeleteQueueModalOpen(false);
  };

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const drawer = (
    <div>
      <DeleteQueueModal
        open={deleteQueueModalOpen}
        handleClickOpen={handleCreateQueueModalOpen}
        handleClose={handleCreateQueueModalClose}
        title="Create Queue"
        content="생성할 큐의 이름을 입력하세요. 알파벳, 한글, -, 숫자만 가능합니다"
      />

      <DeleteQueueModal
        open={deleteQueueModalOpen}
        handleClickOpen={handleDeleteQueueModalOpen}
        handleClose={handleDeleteQueueModalClose}
        title="Delete Queue"
        content="삭제할 큐의 이름을 입력하세요. 알파벳, 한글, -, 숫자만 가능합니다"
      />
      <Divider/>
      <List>
        <ListItem key='Home' disablePadding>
          <ListItemButton>
            <ListItemIcon>
              <HomeIcon/>
            </ListItemIcon>
            <ListItemText primary='Home'/>
          </ListItemButton>
        </ListItem>
      </List>
      <Divider/>
      <List>
        <ListItem key={'Create Queue'} disablePadding onClick={handleCreateQueueModalOpen}>
          <ListItemButton>
            <ListItemIcon>
              <AddCircleOutlineIcon/>
            </ListItemIcon>
            <ListItemText primary={'Create Queue'}/>
          </ListItemButton>
        </ListItem>
        <ListItem key={'Delete Queue'} disablePadding onClick={handleDeleteQueueModalOpen}>
          <ListItemButton>
            <ListItemIcon>
              <DeleteForeverIcon/>
            </ListItemIcon>
            <ListItemText primary={'Delete Queue'}/>
          </ListItemButton>
        </ListItem>
      </List>
    </div>
  );

  const container = window !== undefined ? () => window().document.body : undefined;

  return (
    <Box sx={{display: 'flex'}}>
      <CssBaseline/>
      <AppBar
        position="fixed"
        sx={{
          width: {sm: `calc(100% - ${drawerWidth}px)`},
          ml: {sm: `${drawerWidth}px`},
        }}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{mr: 2, display: {sm: 'none'}}}
          >
            <MenuIcon/>
          </IconButton>
          <Typography variant="h6" noWrap component="div">
            Zola Simple Message Queue
          </Typography>
        </Toolbar>
      </AppBar>
      <Box
        component="nav"
        sx={{width: {sm: drawerWidth}, flexShrink: {sm: 0}}}
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
            display: {xs: 'block', sm: 'none'},
            '& .MuiDrawer-paper': {boxSizing: 'border-box', width: drawerWidth},
          }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          sx={{
            display: {xs: 'none', sm: 'block'},
            '& .MuiDrawer-paper': {boxSizing: 'border-box', width: drawerWidth},
          }}
          open
        >
          {drawer}
        </Drawer>
      </Box>
      <Box
        component="main"
        sx={{flexGrow: 1, p: 3, width: {sm: `calc(100% - ${drawerWidth}px)`}}}
      >
        <Toolbar/>
        {children}
      </Box>
    </Box>
  );
}

ResponsiveDrawer.propTypes = {
  window: PropTypes.func,
};

export default ResponsiveDrawer;