import { Dialog, DialogTitle, List } from "@mui/material";
import PropTypes from 'prop-types';
import AddCarStepper from "../Stepper/AddCarStepper";

function AddCar(props) {
  const { onClose, open } = props;

  const handleClose = () => {
    onClose();
  };

  return (
    <div>
      <Dialog onClose={handleClose} open={open}>
        <DialogTitle>Add Car</DialogTitle>
        <AddCarStepper/>
      </Dialog>
    </div>
  );
};

AddCar.propTypes = {
    onClose: PropTypes.func.isRequired,
    open: PropTypes.bool.isRequired,
}

export default AddCar;
