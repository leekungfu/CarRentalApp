import { Favorite, Key, ManageAccounts, Phone } from "@mui/icons-material";
import {
  Card,
  CardContent,
  CardHeader,
  Container,
  Tab,
  Tabs,
} from "@mui/material";
import React, { useState } from "react";

const MyProfile = () => {
  
  const [tab, setTab] = useState(0);

  const handleChange = (event, value) => {
    setTab(value);
  };
  return (
    <div>
      <Container maxWidth="lg">
        <Card>
          <CardHeader>MY PROFILE</CardHeader>
          <CardContent>
            <Tabs value={tab} onChange={handleChange}>
              <Tab icon={<ManageAccounts />} label="Personal Information" />
              <Tab icon={<Key />} label="Security" />
            </Tabs>
          </CardContent>
        </Card>
      </Container>
    </div>
  );
};

export default MyProfile;
