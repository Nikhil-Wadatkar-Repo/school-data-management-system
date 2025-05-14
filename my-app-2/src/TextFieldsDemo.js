import { getValue } from "@testing-library/user-event/dist/utils";
import React, { useMemo, useState } from "react";
import Button from "@mui/material/Button";
import ButtonGroup from "@mui/material/ButtonGroup";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import Box from "@mui/material/Box";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Modal from "@mui/material/Modal";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import FormHelperText from "@mui/material/FormHelperText";
import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid
import "ag-grid-community/styles/ag-theme-quartz.css";
import {
  Alert,
  AlertTitle,
  Checkbox,
  Grid2,
  IconButton,
  InputAdornment,
  InputLabel,
  MenuItem,
  OutlinedInput,
  Select,
  Stack,
  TextField,
} from "@mui/material";

function TextFieldsDemo() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  let submitButtonArray = {
    Monday: "",
    Tuesday: "",
    Wednesday: "",
    Thursday: "",
    Friday: "",
    Saturday: "",
  };
  const [submitFlag, setSubmitFlag] = useState(submitButtonArray);
  const [disableSubmit, setDisableSubmit] = useState(false);
  const setClick = (dayName, values) => {
    console.log(dayName, " ", values);

    setSubmitFlag({ ...submitFlag, [dayName]: values });
  };
  const submitTimeSheet = () => {
    const keys = Object.keys(submitFlag);
    for (const key of keys) {
      console.log(key + ": " + submitFlag[key]);
    }
  };
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    pt: 2,
    px: 4,
    pb: 3,
  };

  const [columnDefs, setColumnDefs] = useState([
    {
      field: "make",
      editable: true,
      cellEditor: "agSelectCellEditor",
      cellEditorParams: {
        values: [
          "Tesla",
          "Ford",
          "Toyota",
          "Mercedes",
          "Fiat",
          "Nissan",
          "Vauxhall",
          "Volvo",
          "Jaguar",
        ],
      },
    },
    { field: "model" },
    { field: "price", filter: "agNumberColumnFilter" },
    { field: "electric" },
    {
      field: "month",
      comparator: (valueA, valueB) => {
        const months = [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "October",
          "November",
          "December",
        ];
        const idxA = months.indexOf(valueA);
        const idxB = months.indexOf(valueB);
        return idxA - idxB;
      },
    },
  ]);
  // ModuleRegistry.registerModules([ClientSideRowModelModule]);

  // const gridDiv = document.querySelector("#myGrid");

  const rowSelection = {
    mode: "multiRow",
    headerCheckbox: false,
  };

  const defaultColDef = useMemo(() => {
    return {
      filter: "agTextColumnFilter",
      floatingFilter: true,
    };
  }, []);
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: "#fff",
    ...theme.typography.body2,
    padding: theme.spacing(1),

    color: theme.palette.text.secondary,
    ...theme.applyStyles("dark", {
      backgroundColor: "#1A2027",
    }),
  }));
  const currencies = [
    {
      value: "USD",
      label: "$",
    },
    {
      value: "EUR",
      label: "€",
    },
    {
      value: "BTC",
      label: "฿",
    },
    {
      value: "JPY",
      label: "¥",
    },
  ];
  const [showPassword, setShowPassword] = React.useState(false);
  const [checked, setChecked] = React.useState(true);

  const handleChange = (event) => {
    setChecked(event.target.checked);
  };
  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const handleMouseUpPassword = (event) => {
    event.preventDefault();
  };
  const [showAlert, setShowAlert] = useState(false);
  return (
    <div>
      {showAlert ? (
        <>
          <div className="row">
            <div className="col"></div>
            <div className="col">
              <Stack sx={{ width: "100%" }} spacing={2}>
                <Alert variant="outlined" severity="success">
                  <AlertTitle>Success</AlertTitle>
                  This is a success Alert with an encouraging title.
                </Alert>
                {/* <Alert variant="filled" severity="info">
              <AlertTitle>Info</AlertTitle>
              This is an info Alert with an informative title.
            </Alert>
            <Alert severity="warning">
              <AlertTitle>Warning</AlertTitle>
              This is a warning Alert with a cautious title.
            </Alert>
            <Alert severity="error">
              <AlertTitle>Error</AlertTitle>
              This is an error Alert with a scary title.
            </Alert> */}
              </Stack>
            </div>
            <div className="col"></div>
          </div>
        </>
      ) : (
        <></>
      )}

      <div>
        <Button onClick={handleOpen}>Open modal</Button>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="parent-modal-title"
          aria-describedby="parent-modal-description"
        >
          <Box sx={{ ...style, width: 400 }}>
            <h2 id="parent-modal-title">Text in a modal</h2>
            <p id="parent-modal-description">
              Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
            </p>
            <button className="btn btn-primary" onClick={(e) => setOpen(false)}>
              Close
            </button>
          </Box>
        </Modal>
      </div>

      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <h5>TimeSheet</h5>
        </div>
        <div className="col-sm-1"></div>
      </div>

      <br></br>
      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <Box sx={{ display: "flex", flexWrap: "wrap" }}>
            <div>
              <TextField
                label="First Name"
                id="outlined-start-adornment"
                sx={{ m: 1, width: "25ch" }}
                error={true}
                onError={"sss"}
                helperText="invalid inputs"
              />
            </div>
            <div>
              <TextField
                label="Last Name"
                id="outlined-start-adornment"
                sx={{ m: 1, width: "25ch" }}
              />
            </div>
            <div>
              <FormControl sx={{ m: 1, width: "25ch" }} variant="outlined">
                <InputLabel htmlFor="outlined-adornment-password">
                  Password
                </InputLabel>
                <OutlinedInput
                  id="outlined-adornment-password"
                  type={showPassword ? "text" : "password"}
                  endAdornment={
                    <InputAdornment position="end">
                      <IconButton
                        aria-label="toggle password visibility"
                        onClick={handleClickShowPassword}
                        onMouseDown={handleMouseDownPassword}
                        onMouseUp={handleMouseUpPassword}
                        edge="end"
                      >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                      </IconButton>
                    </InputAdornment>
                  }
                  label="Password"
                ></OutlinedInput>
              </FormControl>
            </div>
            <div>
              <FormControl sx={{ m: 1, minWidth: 120 }}>
                <InputLabel id="demo-simple-select-helper-label">
                  Age
                </InputLabel>
                <Select
                  labelId="demo-simple-select-helper-label"
                  id="demo-simple-select-helper"
                  value={12}
                  label="Age"
                >
                  <MenuItem value="">
                    <em>None</em>
                  </MenuItem>
                  <MenuItem value={10}>Ten</MenuItem>
                  <MenuItem value={20}>Twenty</MenuItem>
                  <MenuItem value={30}>Thirty</MenuItem>
                </Select>
                <FormHelperText>With label + helper text</FormHelperText>
              </FormControl>
            </div>
          </Box>
        </div>
        <div className="col-sm-1"></div>
      </div>

      <br></br>
      <div className="row">
        <div className="col-sm-1 "></div>
        <div className="col-sm-10">
          <Box sx={{ display: "flex", flexWrap: "wrap" }}>
            <div>
              <FormControl>
                <FormLabel id="demo-row-radio-buttons-group-label">
                  Gender
                </FormLabel>
                <RadioGroup
                  row
                  aria-labelledby="demo-row-radio-buttons-group-label"
                  name="row-radio-buttons-group"
                >
                  <FormControlLabel
                    value="female"
                    control={<Radio />}
                    label="Female"
                  />
                  <FormControlLabel
                    value="male"
                    control={<Radio />}
                    label="Male"
                  />
                </RadioGroup>
              </FormControl>
            </div>

            <div>
              <FormLabel>Mul</FormLabel>
              <Checkbox
                checked={checked}
                onChange={handleChange}
                inputProps={{ "aria-label": "controlled" }}
              />
            </div>
            <div>
              <FormLabel>Asss</FormLabel>
              <Checkbox
                checked={checked}
                onChange={handleChange}
                inputProps={{ "aria-label": "controlled" }}
              />
            </div>
          </Box>
        </div>
        <div className="col-sm-1"></div>
      </div>
      <br></br>

      <div className="row">
        <div className="col-sm-1"></div>
        <div className="col-sm-10">
          <Box sx={{ display: "flex", flexWrap: "wrap" }}>
            <div>
              <Stack spacing={2} direction="row">
                <Button variant="contained" onClick={handleOpen}>
                  Save
                </Button>
                <Button variant="outlined" onClick={(e) => setShowAlert(false)}>
                  Reset
                </Button>
                <Button variant="text" onClick={(e) => setShowAlert(true)}>
                  Cancel
                </Button>
              </Stack>
            </div>
          </Box>
        </div>
        <div className="col-sm-1"></div>
      </div>
    </div>
  );
}

export default TextFieldsDemo;
