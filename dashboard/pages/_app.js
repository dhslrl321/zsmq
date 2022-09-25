import Head from "next/head";
import {ThemeProvider} from "styled-components";
import {ThemeProvider as MuiThemeProvider} from "@mui/material/styles";

import defaultTheme from "../styles/default-theme";
import muiTheme from "../styles/mui-theme";
import GlobalStyles from "../styles/global-theme";
import Menu from "../components/menu/presenter";

const MyApp = ({Component, pageProps}) => {
  return (
    <>
      <GlobalStyles />
      <Head>
        <title>ZSMQ | Zola Simple Message Queue</title>
      </Head>
      < MuiThemeProvider theme={muiTheme}>
        <ThemeProvider theme={defaultTheme}>
          <Menu>
            <Component/>
          </Menu>
        </ThemeProvider>
      </MuiThemeProvider>
    </>
  )
}

export default MyApp;