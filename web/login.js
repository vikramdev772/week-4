let api = "http://40.0.49.6:4040/api/signup";

let x = document.getElementById("n").value;
let y = document.getElementById("e").value;
let z = document.getElementById("p").value;
let db = {
  name: x,
  email: y,
  password: z,
};
console.log("\n\t js obj :" + db);
console.log("\n\t js obj :" + JSON.stringify(db, null, 2));
async function login() {
  try {
    let res = await fetch(api, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(db),
    });
    let data = await res.text();
    if (res.ok) {
      alert("  login sucess ... ✅ ");
    }
    console.log(" api response : " + data);
  } catch (e) {
    alert(" login failed .. ❌");
  }
}
login();