import React from 'react';

export default function EmployerDashboard() {
  return (
    <div className="py-8">
      <div className="mb-8 flex items-center justify-between">
        <h1 className="text-2xl font-bold text-gray-900">Employer Dashboard</h1>
        <button className="rounded-lg bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-700">
          Post a New Job
        </button>
      </div>

      <div className="mb-8 grid grid-cols-1 gap-6 md:grid-cols-4">
        {[
          ['Active Jobs', '2'],
          ['Pending Applicants', '5'],
          ['Workers Hired', '18'],
          ['Total Spent', 'Rs 2,45,000'],
        ].map(([label, value]) => (
          <div key={label} className="rounded-lg border border-gray-100 bg-white p-6 shadow-sm">
            <h3 className="text-sm font-medium text-gray-500">{label}</h3>
            <p className="mt-2 text-3xl font-bold text-gray-900">{value}</p>
          </div>
        ))}
      </div>

      <div className="overflow-hidden rounded-lg border border-gray-100 bg-white shadow-sm">
        <div className="flex items-center justify-between border-b border-gray-100 px-6 py-4">
          <h2 className="text-lg font-semibold text-gray-900">Manage Postings</h2>
        </div>
        <div className="divide-y divide-gray-100">
          {[
            ['Weekend Cafe Staff', 'Needed: 2 workers, Sat-Sun, 9AM - 3PM', 'Active', '3 applicants', 'View Applicants'],
            ['Warehouse Assistant', 'Needed: 5 workers, next Monday, 8AM - 4PM', 'Filled', 'All positions filled', 'Mark Attendance'],
          ].map(([title, detail, status, stat, action]) => (
            <div key={title} className="p-6 transition-colors hover:bg-gray-50">
              <div className="flex items-start justify-between">
                <div>
                  <h3 className="text-lg font-medium text-gray-900">{title}</h3>
                  <p className="mt-1 text-sm text-gray-500">{detail}</p>
                </div>
                <span className="inline-flex items-center rounded-full bg-green-100 px-2.5 py-0.5 text-xs font-medium text-green-800">
                  {status}
                </span>
              </div>
              <div className="mt-4 flex items-center gap-4">
                <div className="text-sm text-gray-700">{stat}</div>
                <button className="text-sm font-medium text-blue-600 hover:text-blue-800">
                  {action}
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
