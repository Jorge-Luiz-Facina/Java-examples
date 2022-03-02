const express = require('express');
const appSetup = require('./setup/app-setup');
const userRouter = require('./routes/userRouter');

const app = express();

appSetup(app);

app.use('/api/users', userRouter);

app.listen(4545, () => {
	console.log('> Server started on PORT: 4545');
});
